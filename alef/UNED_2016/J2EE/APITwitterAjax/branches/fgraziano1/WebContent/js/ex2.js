function showHint(name)

{

    ex2Script.getPossibleNames(name, updateData);

}

 

function updateData(data)

{

    dwr.util.setValue("txtHint", data);

}