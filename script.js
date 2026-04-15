console.log("JS carregado");

let tarefas = JSON.parse(localStorage.getItem("tarefas")) || [];

function salvarArquivo(){
	localStorage.setItem("tarefas", JSON.stringify(tarefas));
}

function adicionarTarefa(){
	const input = document.getElementById("inputTarefa");
	const texto = input.value;
	
	if (texto == "") return;
	
	const tarefa = {
		id: tarefas.length + 1,
		titulo: texto,
		concluida: false
	};
	
	tarefas.push(tarefa);
	input.value = "";

	salvarArquivo();
	renderizar();
}

function renderizar (){

const listaPendentes = document.getElementById("listaPendentes");
const listaConcluidas = document.getElementById("listaConcluidas");

listaPendentes.innerHTML = "";
listaConcluidas.innerHTML = "";

tarefas.forEach (t => {
	const li = document.createElement("li");
	
	li.innerHTML = `
		${t.id} - <span class = "${t.concluida ? 'concluida': ''}">
				${t.titulo}
			</span>
		<button onclick = "concluir(${t.id})">Concluir</button>
		<button onclick = "remover(${t.id})">Remover</button>
		`;
		
		if (t.concluida){
			listaConcluidas.appendChild(li);
			
		}else {
			listaPendentes.appendChild(li);
		}
		
			
		});
	}
	
function concluir (id){
	const tarefa = tarefas.find(t => t.id === id);
	if (tarefa){
		tarefa.concluida = true;
	}
	salvarArquivo();
	renderizar();
}

function remover (id){
	tarefas = tarefas.filter(t => t.id !== id);
	salvarArquivo();
	renderizar();
}

renderizar();
