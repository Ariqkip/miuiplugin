import { ImagePickerMiui } from 'image-picker-miui';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    ImagePickerMiui.echo({ value: inputValue })
}
