function download (file){


    var link = document.getElementById("downlink")
    link.setAttribute("download", file)
    link.click()
    
};