

window.onload = function() {
    verificarLogin();
    listarCursos();
}



async function listarCursos() {
    const token = localStorage.getItem("token");
    try {
        const response = await fetch(API_CURSOS, {
            headers: { "Authorization": `Bearer ${token}` }
        });

        if (!response.ok) throw new Error("Erro ao listar cursos");

        const cursos = await response.json();
        const tbody = document.querySelector("#tabela-cursos tbody");
        tbody.innerHTML = "";

        cursos.forEach(curso => {
            const tr = document.createElement("tr");
            tr.innerHTML = `
                <td>${curso.id}</td>
                <td>${curso.nome}</td>
                <td>${curso.descricao}</td>
                <td>
                    <button onclick="editarCurso(${curso.id})">Editar</button>
                    <button onclick="deletarCurso(${curso.id})">Excluir</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        alert(error.message);
    }
}


function mostrarFormCadastroCurso() {
    const form = document.getElementById("form-cadastro-curso");
    form.style.display = form.style.display === "none" ? "block" : "none";
}


async function cadastrarCurso() {
    const nome = document.getElementById("nomeCurso").value;
    const descricao = document.getElementById("descricaoCurso").value;
    const token = localStorage.getItem("token");

    if (!nome || !descricao) {
        alert("Preencha nome e descrição!");
        return;
    }

    try {
        const response = await fetch(API_CURSOS, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({ nome, descricao })
        });

        if (!response.ok) throw new Error("Erro ao cadastrar curso");

        document.getElementById("nomeCurso").value = "";
        document.getElementById("descricaoCurso").value = "";
        document.getElementById("form-cadastro-curso").style.display = "none";

        listarCursos();
    } catch (error) {
        alert(error.message);
    }
}


async function deletarCurso(id) {
    const token = localStorage.getItem("token");
    if (!confirm("Tem certeza que deseja excluir este curso?")) return;

    try {
        const response = await fetch(`${API_CURSOS}/${id}`, {
            method: "DELETE",
            headers: { "Authorization": `Bearer ${token}` }
        });

        if (!response.ok) throw new Error("Erro ao deletar curso");

        listarCursos();
    } catch (error) {
        alert(error.message);
    }
}


async function editarCurso(id) {
    const token = localStorage.getItem("token");

    try {
        const response = await fetch(`${API_CURSOS}/${id}`, {
            headers: { "Authorization": `Bearer ${token}` }
        });
        if (!response.ok) throw new Error("Erro ao buscar curso");

        const curso = await response.json();

        const form = document.getElementById("form-cadastro-curso");
        form.style.display = "block";
        document.getElementById("nomeCurso").value = curso.nome;
        document.getElementById("descricaoCurso").value = curso.descricao;

        const btnSalvar = form.querySelector("button");
        btnSalvar.textContent = "Atualizar";
        btnSalvar.onclick = async function() {
            const novoNome = document.getElementById("nomeCurso").value;
            const novaDescricao = document.getElementById("descricaoCurso").value;

            try {
                const putResponse = await fetch(`${API_CURSOS}/${id}`, {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": `Bearer ${token}`
                    },
                    body: JSON.stringify({ nome: novoNome, descricao: novaDescricao })
                });

                if (!putResponse.ok) throw new Error("Erro ao atualizar curso");

                form.style.display = "none";
                document.getElementById("nomeCurso").value = "";
                document.getElementById("descricaoCurso").value = "";

                btnSalvar.textContent = "Salvar";
                btnSalvar.onclick = cadastrarCurso;

                listarCursos();
            } catch (error) {
                alert(error.message);
            }
        };
    } catch (error) {
        alert(error.message);
    }
}