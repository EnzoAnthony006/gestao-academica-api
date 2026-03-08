async function fazerLogin() {
    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;
    const mensagemErro = document.getElementById("mensagem-erro");

    mensagemErro.textContent = "Carregando...";
    mensagemErro.style.color = "blue";

    try {

        const response = await fetch(API_LOGIN, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify({ email: email, senha: senha })
        });

        if (!response.ok) {

            throw new Error("E-mail ou senha inválidos no sistema");
        }

        const data = await response.json();

        if (data.token) {

            localStorage.setItem("token", data.token);
            window.location.href = "alunos.html";
        } else {
            throw new Error("Falha na geração do acesso");
        }

    } catch (error) {
        console.error("Erro detalhado:", error);
        mensagemErro.textContent = error.message;
        mensagemErro.style.color = "red";
    }
}