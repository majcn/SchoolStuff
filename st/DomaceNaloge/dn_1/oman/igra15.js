var N = 4;
	var tabelaSt = new Array(N);
	var tab = new Array();
		for(var z=0;z<N;z++){
			tabelaSt[z] = new Array(N);
	}
	
	function zacetek(){
	
	var k = 0;
		//stevila od 1-15
		for(var u=0;u<N*N;u++){
			tab[u]=u;
		}
		
		//Polnenje tabele
		var Do = N*N;
		for(var i=0;i<N;i++){
			for(var j=0;j<N;j++){
				k = Math.floor(Math.random()*Do);
				tabelaSt[i][j] = tab[k];
				tab.splice(k,1);
				Do--;
				
			}
		}
		
	}
	
	function preveri(){
	var resen = true;
	k = 0;
		for(var i=0;i<N;i++){
			for(var j=0;j<N;j++){
				if(tabelaSt[i][j]!=k){
					resen = false;
				}
				k++;
			}
		}
		return resen;
	
	}
	
	function p(evt){
		//dobimo ID objekta, ki je sprozil event
		var evt = evt || window.event; 
		var target = evt.target || window.event.srcElement;
		var objID = target.getAttribute("id"); 
				
		//dobimo stevilo v polju
		element = document.getElementById(objID);
		var stPolja = element.innerHTML;
		
		
		//ID je enak, kot je stevilka v tabeli...poiscemo to stevilko, se zapomnimo
		//koordinate in pogledamo ce je sosednja 0 in jih zamenenjamo
		var x=0;
		var y=0;
		for(var i=0;i<N;i++){
			for(var j=0;j<N;j++){
				if(tabelaSt[i][j]==stPolja){
					x = i;
					y = j;
					
				}
			}
		}
		if(tabelaSt[x][y]!=0){
		//Preveri zgoraj
		if(x>0 && tabelaSt[x-1][y]==0){
			prvi = document.getElementById("el"+tabelaSt[x][y]);
			drugi = document.getElementById("el0");
			prvi.id = "el0";
			drugi.id = "el"+tabelaSt[x][y];
			
			tabelaSt[x-1][y] = tabelaSt[x][y];
			tabelaSt[x][y] = 0;
			
		}
		//Spodaj
		else if(x<3 && tabelaSt[x+1][y]==0){
			prvi = document.getElementById("el"+tabelaSt[x][y]);
			drugi = document.getElementById("el0");
			prvi.id = "el0";
			drugi.id = "el"+tabelaSt[x][y];
			
			tabelaSt[x+1][y] = tabelaSt[x][y];
			tabelaSt[x][y] = 0;
		}
		//levo
		else if(y>0 && tabelaSt[x][y-1]==0){
			prvi = document.getElementById("el"+tabelaSt[x][y]);
			drugi = document.getElementById("el0");
			prvi.id = "el0";
			drugi.id = "el"+tabelaSt[x][y];
			
			tabelaSt[x][y-1] = tabelaSt[x][y];
			tabelaSt[x][y] = 0;
		}
		//desno
		else if(y<3 && tabelaSt[x][y+1]==0){
			prvi = document.getElementById("el"+tabelaSt[x][y]);
			drugi = document.getElementById("el0");
			prvi.id = "el0";
			drugi.id = "el"+tabelaSt[x][y];
			
			tabelaSt[x][y+1] = tabelaSt[x][y];
			tabelaSt[x][y] = 0;
		}
		zamenjaj();
		
		
		}
		if(preveri()==true){
			alert("Bravo!");
			location.reload(true);
		}
		
		return false;
	}
	
	function zamenjaj(){
	var k=0;
		for(var i=0;i<N;i++){
			for(var j=0;j<N;j++){
				element = document.getElementById("el"+k);
				element.innerHTML = k;
				k++;
			}
		}

	}	
	
	function f(){
		zacetek();
		element = document.getElementById("tabela");
		var k=0;
		for(var i=0;i<N;i++){
			for(var j=0;j<N;j++){
				novi = document.createElement("th");
				novi.id = "el"+tabelaSt[i][j];
				novi.innerHTML = tabelaSt[i][j];
				novi.onclick = p;//("click",p,false);
				k+=1;
				
				element.appendChild(novi);
			}
			novi = document.createElement("tr");
			
			element.appendChild(novi);
		}
}