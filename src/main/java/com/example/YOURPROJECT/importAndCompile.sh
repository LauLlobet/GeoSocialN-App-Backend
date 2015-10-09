tar -zxvf safe.tar.gz
cp -R ~/Desktop/otjavabackend/src/main/java/com/tubtale/otbackend/* .;
rm *Server *;
rm *Application*;
rm *Hibernate*;
cp safe/* .;
rm -rf safe/;
find . -name "*.java" | xargs sed -i 's/package com.tubtale.otbackend;/package com.example.YOURPROJECT;/g'
