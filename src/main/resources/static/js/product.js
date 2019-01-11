setTimeout(function() {
    $("#alert").fadeOut(1000);
}, 4000);

function handleEdit() {
    document.getElementById('name2').disabled = false;
    document.getElementById('postcode').disabled = false;
    document.getElementById('edit').hidden = true;
    document.getElementById('save').hidden = false;

    return false;
}