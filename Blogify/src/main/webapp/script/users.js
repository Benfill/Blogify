    // Function to clean the URL parameters
    function cleanUrl() {
    // Use history.replaceState to change the URL without reloading
    history.replaceState(null, '', window.location.pathname);
}

    // Wait for 3 seconds before cleaning the URL
    window.onload = function() {
    setTimeout(cleanUrl, 3000); // 3000 milliseconds = 3 seconds
};