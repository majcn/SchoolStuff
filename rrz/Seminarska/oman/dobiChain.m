%dobi nenatancno chain kodo slike
%ni pravilna chain koda, pravilno je samo sodo ali liho
%zamesa 2 z 4 ali 6....
%pomembno je samo, da ne zamesa lihe s sodo, ker samo to rabim
function [chain] = dobiChain(slika)

velikost = size(slika);
v = velikost(1)*velikost(2);
stolpci = velikost(1);

obris = [];

%dobimo obris
slika1 = dobiObris(slika);

dol = 0;
desno = 1;
regija = 0;
%kje se zacne regija
for j=1:v
	dol++;
	if(dol>stolpci)
		dol = 1;
		desno+=1;
	end;
	if(slika1(j)<1)
		regija = j;
		break;
		
	end;
end;

kdol = [];
kdesno = [];
i = regija;
konec = 0;
dolZ = dol;
desnoZ = desno;
slika2 = zeros(size(slika1));
napaka = 0;
%%v kdol in kdesno doda koordinate obrisa
%%more biti samo obris, drugace se zatakne
cas = 0;
while(konec == 0)
	cas++;
	ze = 0;
	if((i+1)<=v && slika1((i+1))<1 && ze == 0 && slika2(i+1)==0)		%[0 0 0; 0 0 0; 0 1 0]
		ze = 1;
		i = i+1;
		kdol = [kdol dol];
		kdesno = [kdesno desno];
		dol++;
		slika2(i) = 1;
		
		
	end;
	if((i+stolpci+1)<=v && slika1((i+stolpci+1))<1 && ze == 0 && slika2(i+stolpci+1)==0)				%[0 0 0; 0 0 0; 0 0 1]
		ze = 1;
		i = i+stolpci+1;
		kdol = [kdol dol];
		kdesno = [kdesno desno];
		dol++;
		desno++;
		slika2(i) = 1;
	end;
	if((i+stolpci)<=v && slika1((i+stolpci))<1 && ze == 0 && slika2(i+stolpci)==0)					%[0 0 0; 0 0 1; 0 0 0]
		ze = 1;
		i = i+stolpci;
		kdol = [kdol dol];
		kdesno = [kdesno desno];
		desno++;
		slika2(i) = 1;
	end;
	if((i+stolpci-1) > 0 && slika1((i+stolpci-1))<1 && ze == 0 && slika2(i+stolpci-1)==0)			%[0 0 1; 0 0 0; 0 0 0]
		ze = 1;
		i = i+stolpci-1;
		kdol = [kdol dol];
		kdesno = [kdesno desno];
		desno++;
		dol--;
		slika2(i) = 1;
	end;
	if((i-1) > 0 && slika1((i-1))<1 && ze == 0 && slika2(i-1)==0)			%[0 1 0; 0 0 0; 0 0 0]
		ze = 1;
		i = i-1;
		kdol = [kdol dol];
		kdesno = [kdesno desno];
		dol--;
		slika2(i) = 1;
	end;
	if((i-stolpci-1) > 0 && slika1((i-stolpci-1))<1 && ze == 0 && slika2(i-stolpci-1)==0)			%[1 0 0; 0 0 0; 0 0 0]
		ze = 1;
		i = i-stolpci-1;
		kdol = [kdol dol];
		kdesno = [kdesno desno];
		desno--;
		dol--;
		slika2(i) = 1;
	end;
	if((i-stolpci) > 0 && (slika1(i-stolpci))<1 && ze == 0 && slika2(i-stolpci)==0)								%[0 0 0; 1 0 0; 0 0 0]
		ze = 1;
		i = i-stolpci;
		kdol = [kdol dol];
		kdesno = [kdesno desno];
		desno--;
		slika2(i) = 1;
	end;
	if((i-stolpci+1) < v && slika1((i-stolpci+1))<1 && ze == 0 && slika2(i-stolpci+1)==0)				%[0 0 0; 0 0 0; 1 0 0]
		ze = 1;
		i = i-stolpci+1;
		kdol = [kdol dol];
		kdesno = [kdesno desno];
		desno--;
		dol++;
		slika2(i) = 1;
	end;
	konec = 0;
	if(cas>10000)
		konec = 1;
	end;
	if(i == regija)
		konec = 1;
	end;
	
end;
	
cdol = [];
cdesno = [];
chain = [];
%dobimo tiste cifre, iz katerih se potem dobi chain koda
%if(napaka==0)
for j=1:length(kdol)
	if(j==length(kdol))
		cdol = [cdol (kdol(1)-kdol(j))];
		cdesno = [cdesno (kdesno(1)-kdesno(j))];
		
	else
		cdol = [cdol (kdol(j+1)-kdol(j))];
		cdesno = [cdesno (kdesno(j+1)-kdesno(j))];
	end;

end;



%dobimo dejansko chain kodo
for j=1:length(cdol)
	if(cdol(j)==1)
		if(cdesno(j)==0)
			chain = [chain 0];
		end;
		if(cdesno(j)==1)
			chain = [chain 1];
		end;	
		if(cdesno(j)==-1)
			chain = [chain 7];
		end;
	end;
	if(cdol(j)==0)
		if(cdesno(j)==1)
			chain = [chain 2];
		end;	
		if(cdesno(j)==-1)
			chain = [chain 6];
		end;
	end;
	if(cdol(j)==-1)
		if(cdesno(j)==1)
			chain = [chain 3];
		end;	
		if(cdesno(j)==0)
			chain = [chain 4];
		end;	
		if(cdesno(j)==-1)
			chain = [chain 5];
		end;	
	end;
	


end;
%else chain = 0;
%end;


end;