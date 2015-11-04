tar -zxvf safe.tar.gz
cp -R ~/Desktop/otjavabackend/src/main/java/com/tubtale/otbackend/* .;
rm *Server*;
rm *Application*;
rm *Hibernate*;
cp safe/* .;
rm -rf safe/;
find . -name "*.java" | xargs sed  -i '' -e  's/com.tubtale.otbackend/com.example.YOURPROJECT/g'
cd ../../../../../../
git add -A
git commit -m "TO COMPILAR to compile server"
git push
ssh -i /Users/quest/.ssh/robinKeyPair.pem ubuntu@52.10.176.122
# gradle war // will compileº
