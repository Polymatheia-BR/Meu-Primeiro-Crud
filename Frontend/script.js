let tbody = document.getElementsByTagName("tbody")[0];

function carregarClientes() {
    //Consumo da API e adição de dados na tabela
    fetch('http://localhost:8080/clientes')
        .then((response) => response.json())
        .then((clienteArr) => {
            clienteArr.forEach(e => {
                adicionarCliente(e);
            });
        })
        .catch((err) => {
            console.log(err);
            setTimeout(() => {
                carregarClientes();
            }, 5000);
        });
}

function adicionarCliente(cliente) {
    let row = tbody.insertRow();
    row.setAttribute("id", cliente.id);
    row.insertCell(0).outerHTML = `<th scope="row">${cliente.id}</th>`;

    row.insertCell().innerHTML = cliente.name;
    row.insertCell().innerHTML = cliente.email;
    row.insertCell().innerHTML = formatarTelefone(cliente.telephone);
    let deletar = row.insertCell();
    deletar.setAttribute("onclick", `deletarCliente(${cliente.id})`);
    deletar.innerHTML = '<i class="bi bi-x-square deleteBtn"></i>';
    let editar = row.insertCell();
    editar.setAttribute("onclick", `editarClienteModal(${cliente.id})`);
    editar.innerHTML = '<i class="bi bi-pencil-square editBtn" data-bs-toggle="modal" data-bs-target="#editarClienteModal"></i>';
}

function deletarCliente(id) {
    fetch("http://localhost:8080/cliente/" + id, {
        method: "DELETE"
    });
    document.getElementById(id).remove();
}

function editarClienteModal(id) {
    let row = document.getElementById(id);
    document.getElementById("nameEdit").value = row.children[1].innerHTML;
    document.getElementById("emailEdit").value = row.children[2].innerHTML;
    document.getElementById("telephoneEdit").value = row.children[3].innerHTML;
    
    //Formulario de edição de clientes
    formEdit = document.getElementById('sendEditData');
    formEdit.addEventListener('submit', function () {
        const editDataForm = new FormData(formEdit);
        editDataForm.set('telephone', editDataForm.get('telephone').replace(/\D/g, ""));
        let editDataJson = JSON.stringify(Object.fromEntries(editDataForm));
        
        console.log(editDataJson);

        fetch("http://localhost:8080/cliente/" + id, {
            headers: {
                "Content-Type": "application/json"
            },
            method: "PUT",
            body: editDataJson
        }).then(carregarClientes());
    });
}

//Formulario de criação de Clientes
form = document.getElementById('sendData');
form.addEventListener('submit', function() {
    const dataForm = new FormData(form);
    dataJson = JSON.stringify(Object.fromEntries(dataForm));
    
    fetch("http://localhost:8080/clientes", {
        headers: {
            "Content-Type": "application/json"
        },
        method: "POST",
        body: dataJson
    }).then(carregarClientes());
    
});

function formatarTelefone(phone) {
    const matches = phone.matchAll(
        /(?<DDI>\d{2})(?<DDD>\d{2})(?<Left>\d{5})(?<Right>\d{4})/g
    );
    const newPhones = [...matches].map(({ groups }) => {
        const { DDI, DDD, Left, Right } = groups;
        return `+${DDI} (${DDD}) ${Left}-${Right}`;
    });
    return newPhones;
}


//Formatar input enquanto escreve
input = document.getElementById('telephoneEdit');
input.addEventListener("keyup", () => {
    input.value = input.value.replace(/^(\d{2})(\d{2})(\d{5})(\d{4})$/, "+$1 ($2) $3-$4");
});

window.onload = carregarClientes();