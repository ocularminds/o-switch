#! /bin/bash
 
local_file="$(ls $TRAVIS_BUILD_DIR/typepad-dist/target/*.zip | head -n 1)"
target_url='ftp://doepner.net/~/public_html/dev/dist/ci-builds/typepad.zip'
 
echo "Uploading $local_file to $target_url"
curl -u $FTP_USER:$FTP_PASSWORD -T "$local_file" "$target_url"