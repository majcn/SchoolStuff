function Game(gameID)
{
	this.winNumbers = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]];
	this.numbers = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]];
	this.varTable = document.getElementById(gameID).getElementsByTagName('TD');
	this.randomNumbers = function()
	{
		var zeIzbrani = new Array();
		var niPonovitev = true;
		for(var i=0; i<4; i++)
		{
			for(var j=0; j<4; j++)
			{
				this.numbers[i][j] = Math.floor(Math.random()*16)+1;
				for(x in zeIzbrani)
				{
					if(this.numbers[i][j] == zeIzbrani[x])
					{
						j--;
						niPonovitev = false;
						break;
					}
					else
						niPonovitev = true;
				}
				if(niPonovitev)
				{
					zeIzbrani.push(this.numbers[i][j]);
				}
			}
		}
		this.izpis();
	}
	this.prestavi = function(pozicijaI, pozicijaJ)
	{
		if(pozicijaI > 0)
		{
			if(this.numbers[pozicijaI-1][pozicijaJ] == 16)
			{
				this.numbers[pozicijaI-1][pozicijaJ] = this.numbers[pozicijaI][pozicijaJ];
				this.numbers[pozicijaI][pozicijaJ] = 16;
			}
		}
		if(pozicijaI < 3)
		{
			if(this.numbers[pozicijaI+1][pozicijaJ] == 16)
			{
				this.numbers[pozicijaI+1][pozicijaJ] = this.numbers[pozicijaI][pozicijaJ];
				this.numbers[pozicijaI][pozicijaJ] = 16;
			}
		}
		if(pozicijaJ > 0)
		{
			if(this.numbers[pozicijaI][pozicijaJ-1] == 16)
			{
				this.numbers[pozicijaI][pozicijaJ-1] = this.numbers[pozicijaI][pozicijaJ];
				this.numbers[pozicijaI][pozicijaJ] = 16;
			}
		}
		if(pozicijaJ < 3)
		{
			if(this.numbers[pozicijaI][pozicijaJ+1] == 16)
			{
				this.numbers[pozicijaI][pozicijaJ+1] = this.numbers[pozicijaI][pozicijaJ];
				this.numbers[pozicijaI][pozicijaJ] = 16;
			}	
		}
		this.izpis();
		this.checkWin();
	}
	this.izpis = function()
	{
		for(var i=0; i<this.varTable.length; i++)
		{
			this.varTable[i].innerHTML = this.numbers[Math.floor(i/4)][i%4];
			if(this.varTable[i].innerHTML == 16)
			{
				this.varTable[i].innerHTML = "";
				this.varTable[i].style.background = "black";
			}
			else
				this.varTable[i].style.background = "none";
		}
	}
	this.checkWin = function()
	{
		if(this.numbers.toString() == this.winNumbers.toString())
			alert("resili ste igro");
	}
}