password=$(curl -silent -i -H Accept: application/json -X GET http://127.0.0.1/YOUR_PATH/trees/);

if grep -q treeContent <<< "$password" ; then
    echo "server is ok"
else
    echo "server is no ok"
fi


