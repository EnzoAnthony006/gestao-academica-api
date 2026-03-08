window.onload = function () {
    verificarLogin();
    listarAlunos();
}

async function listarAlunos() {
    const token = localStorage.getItem("token");

    try {
        const response = await fetch(API_ALUNOS, {
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) throw new Error("Erro ao listar alunos");

        const alunos = await response.json();
        const tbody = document.querySelector("#tabela-alunos tbody");
        tbody.innerHTML = "";

        alunos.forEach(aluno => {
            const tr = document.createElement("tr");

            tr.innerHTML = `
                <td>${aluno.id}</td>
                <td>${aluno.nome}</td>
                <td>${aluno.email}</td>
                <td>
                    <button onclick="editarAluno(${aluno.id})">Editar</button>
                    <button onclick="deletarAluno(${aluno.id})">Excluir</button>
                </td>
            `;

            tbody.appendChild(tr);
        });

    } catch (error) {
        alert(error.message);
    }
}

async function cadastrarAluno() {
    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    const token = localStorage.getItem("token");

    if (!nome || !email) {
        alert("Preencha nome e email!");
        return;
    }

    try {
        const response = await fetch(API_ALUNOS, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({ nome, email })
        });

        if (!response.ok) throw new Error("Erro ao cadastrar aluno");

        alert("Aluno cadastrado com sucesso!");

        document.getElementById("nome").value = "";
        document.getElementById("email").value = "";
        document.getElementById("form-cadastro").style.display = "none";

        listarAlunos();

    } catch (error) {
        alert(error.message);
    }
}

async function deletarAluno(id) {
    const token = localStorage.getItem("token");

    if (!confirm("Tem certeza que deseja excluir este aluno?")) return;

    try {
        const response = await fetch(`${API_ALUNOS}/${id}`, {
            method: "DELETE",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) throw new Error("Erro ao deletar aluno");

        alert("Aluno deletado com sucesso!");

        listarAlunos();

    } catch (error) {
        alert(error.message);
    }
}

async function editarAluno(id) {
    const token = localStorage.getItem("token");

    try {

        const response = await fetch(`${API_ALUNOS}/${id}`, {
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) throw new Error("Erro ao buscar aluno");

        const aluno = await response.json();

        const form = document.getElementById("form-cadastro");
        form.style.display = "block";

        document.getElementById("nome").value = aluno.nome;
        document.getElementById("email").value = aluno.email;

        const btnSalvar = form.querySelector("button");
        btnSalvar.textContent = "Atualizar";

        btnSalvar.onclick = async function () {

            const novoNome = document.getElementById("nome").value;
            const novoEmail = document.getElementById("email").value;

            try {

                const putResponse = await fetch(`${API_ALUNOS}/${id}`, {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": `Bearer ${token}`
                    },
                    body: JSON.stringify({
                        nome: novoNome,
                        email: novoEmail
                    })
                });

                if (!putResponse.ok) throw new Error("Erro ao atualizar aluno");

                alert("Aluno atualizado com sucesso!");

                form.style.display = "none";

                document.getElementById("nome").value = "";
                document.getElementById("email").value = "";

                btnSalvar.textContent = "Salvar";
                btnSalvar.onclick = cadastrarAluno;

                listarAlunos();

            } catch (error) {
                alert(error.message);
            }
        };

    } catch (error) {
        alert(error.message);
    }
}

function mostrarFormCadastro() {
    const form = document.getElementById("form-cadastro");

    if (form.style.display === "none") {
        form.style.display = "block";
    } else {
        form.style.display = "none";
    }
}

function logout() {
    localStorage.removeItem("token");
    window.location.href = "login.html";
}