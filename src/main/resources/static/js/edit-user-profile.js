function handleEdit() {
    document.getElementById('name').disabled = false;
    document.getElementById('secondName').disabled = false;
    document.getElementById('phoneNumber').disabled = false;
    document.getElementById('edit').hidden = true;
    document.getElementById('save').hidden = false;

    return false;
}