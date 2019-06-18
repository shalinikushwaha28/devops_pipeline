#!/usr/bin/env
from git import Repo

def TagRepo(path, tag):
    repo = Repo(path)
    repo.create_tag(tag)
    repo.remotes.origin.push(tag)


if __name__ == "__main__":

    parser = optparse.OptionParser('usage: %prog [options] ')
    parser.add_option('-p', '--path', dest='path', help='path to repo')
    parser.add_option('-t', '--tag', dest='tag', help='Tag label')

    (options, args) = parser.parse_args()

    TagRepo(options.path, options.tag)
