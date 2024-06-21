console.log("hey");
const baseURL = "http://localhost:8081";
const viewContactModal = document.getElementById('view_contact_modal')

// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

//instance options object  
const instanceOptions = {
    id: 'view_contact_modal',
    override: true
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

function openContactModal(){
    contactModal.show();
}

function closeContactModal(){
    contactModal.hide();
}

async function loadContactdata(id){
    console.log(id);

    try{
        const data = await(
            await fetch(`${baseURL}/api/contacts/${id}`)
        ).json();
        console.log(data);
        document.querySelector("#contact_name").innerHTML = data.name;
        document.querySelector("#contact_email").innerHTML = data.email;
        openContactModal();
    }
    catch (error){
        console.log("Error: ", error);
    }

}

async function deleteContact(id){
    Swal.fire({
        title: "Do you want to delete the changes?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Save",
      }).then((result) => {
        
        if (result.isConfirmed) {
          const url = `${baseURL}/user/contacts/delete/` + id;
          window.location.replace(url);
        } 
      });
}