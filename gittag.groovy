stage('tag build'){
checkout([
    $class: 'GitSCM', branches: [[name: '*/master']],
    userRemoteConfigs: [[credentialsId: 'git',
    url: 'ssh://ssh kmaster@kmaster-demo.eastus.cloudapp.azure.com']],
    extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'targeted-dir']]
])

sshagent(credentials: ['<credentials ID.']){
  dir('targeted-dir'){
    sh("git config user.email 'suneelmaurya25@gmail.com")
    sh("git config user.name 'SuneelMaurya'")

    // deletes current snapshot tag
    sh ("git tag -d ${PARAM_VERSION_NUMBER} || true")
    // tags current changeset
    sh ("git tag -a ${PARAM_VERSION_NUMBER} -m \"versioning ${PARAM_VERSION_NUMBER}\"")
    // deletes tag on remote in order not to fail pushing the new one
    sh ("git push origin :refs/tags/snapshot")
    // pushes the tags
    sh ("git push --tags")
    }
}
