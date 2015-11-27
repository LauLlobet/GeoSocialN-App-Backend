cd backendToCompile/YOUR_PROJECT_DIR/
git pull origin master
gradle war
cd
cp ./backendToCompile/YOUR_PROJECT_DIR/build/libs/YOUR_PROJECT_DIR.war ./YOUR_PATH.war
./run.sh
