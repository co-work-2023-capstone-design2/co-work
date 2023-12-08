// drag-and-drop event
document.querySelector("#drop-zone").addEventListener("dragover", (event) => {
    event.preventDefault();
    event.stopPropagation();
});

document.querySelector("#drop-zone").addEventListener("drop", (event) => {
    event.preventDefault();
    event.stopPropagation();

    // put furniture
    $("#furniture").attr("src", "img/sofa1.png");
    const furnitureElement = document.querySelector("#img-furniture");
    const clonedFurnitureElement = furnitureElement.cloneNode(); // copy
    // copy position setting
    const posX = event.offsetX + 50;
    const posY = event.offsetY + 100;
    clonedFurnitureElement.style.width = "100px";
    clonedFurnitureElement.style.height = "100px";
    clonedFurnitureElement.style.left = posX + "px";
    clonedFurnitureElement.style.top = posY + "px";
    clonedFurnitureElement.style.position = "fixed";
    clonedFurnitureElement.style.zIndex = "1";
    // input copy to drop container
    let newFurniture = document
        .querySelector("#drop-zone")
        .appendChild(clonedFurnitureElement);
    newFurniture.id = "1"; // todo: change to random id
});