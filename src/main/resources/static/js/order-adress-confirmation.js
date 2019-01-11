function handleEdit() {
    document.getElementById('street').disabled = false;
    document.getElementById('houseNumber').disabled = false;
    document.getElementById('apartmentNumber').disabled = false;
    document.getElementById('postalCode').disabled = false;
    document.getElementById('city').disabled = false;
    document.getElementById('editOrderAdress').hidden = true;
    document.getElementById('saveOrderAdress').hidden = false;

    return false;
}