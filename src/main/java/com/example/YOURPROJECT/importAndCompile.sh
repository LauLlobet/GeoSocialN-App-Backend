tar -zxvf safe.tar.gz
cp -R ~/Desktop/otjavabackend/src/main/java/com/tubtale/otbackend/* .;
rm *Server*;
rm *Application*;
rm *Hibernate*;
cp safe/* .;
rm -rf safe/;
find . -name "*.java" | xargs sed  -i '' -e  's/com.tubtale.otbackend/com.example.YOURPROJECT/g'
# gradle war // will compileº
