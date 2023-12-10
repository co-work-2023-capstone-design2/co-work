document.querySelectorAll(".furniture").forEach((element) => {
    element.addEventListener("dragstart", (event) => {
        event.dataTransfer.setData("text/plain", event.target.id);
        console.log(event.target.id);
    })
});

document.querySelector("#drop-zone").addEventListener("dragover", (event) => {
    event.preventDefault();
    event.stopPropagation();
});

document.querySelector("#drop-zone").addEventListener("drop", (event) => {
    event.preventDefault();
    event.stopPropagation();

    // put furniture
    const furnitureId = event.dataTransfer.getData("text/plain");
    console.log(furnitureId);
    const furnitureElement = document.querySelector(`#${furnitureId}`);
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
