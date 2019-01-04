function handleEdit() {
    document.getElementById('street').disabled = false;
    document.getElementById('houseNumber').disabled = false;
    document.getElementById('apartmentNumber').disabled = false;
    document.getElementById('postalCode').disabled = false;
    document.getElementById('city').disabled = false;
    document.getElementById('edit').hidden = true;
    document.getElementById('save').hidden = false;

    return false;
}