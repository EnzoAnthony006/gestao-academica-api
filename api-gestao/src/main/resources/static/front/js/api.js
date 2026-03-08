const API_BASE = "http://localhost:8080";

const API_ALUNOS = `${API_BASE}/alunos`;
const API_CURSOS = `${API_BASE}/cursos`;
const API_LOGIN = `${API_BASE}/auth/login`;

function verificarLogin() {
    const token = localStorage.getItem("token");

    if (!token) {
        console.warn("Token não encontrado, redirecionando para login...");
        window.location.href = "login.html";
        return null;
    }

    return token;
}

function logout() {
    localStorage.removeItem("token");
    console.log("Usuário deslogado.");
    window.location.href = "login.html";
}


async function tratarResposta(response) {
    if (!response.ok) {
        const erroTexto = await response.text();
        console.error("Erro na requisição:", response.status, erroTexto);
        throw new Error(erroTexto || "Erro na comunicação com o servidor");
    }
    return response.json();
}