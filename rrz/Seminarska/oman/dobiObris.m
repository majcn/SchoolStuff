%0-Crna
%1 ali vecje - Bela(regija)
function [slika2] = dobiObris(slika)

%dobimo veliksot slike
velikost = size(slika);
v = velikost(1)*velikost(2);
stolp = velikost(1);
slika1 = slika;

slika2 = zeros(size(slika1));
%Sprehodimo se cez sliko in najdemo indekse obrisa
%ce je prejsnji piksel prazen in nasleden ni prazen je to del obrisa
%ce je nasledni piksel prazen in prejsnji ni prazen je to del obrisa
%tako pregleda za vse 4 smeri
for i=1:v
	if(slika1(i)>0)
	if(((i-1)>0 && (i+1)<v && slika1(i-1)>=1 && slika1(i+1)<1) 
		|| ((i-stolp)>0 && (i+stolp)<v && slika1(i-stolp)>=1 && slika1(i+stolp)<1) 
		%%|| ((i-stolp-1)>0 && slika1(i-stolp-1)<1 && slika1(i+stolp+1)>=1)
		%%|| ((i-stolp+1)>0 && slika1(i-stolp+1)<1 && slika1(i+stolp-1)>=1) 
		|| ((i-1)>0 && (i+1)<v && slika1(i-1)<1 && slika1(i+1)>=1)
		|| ((i-stolp)>0 && (i+stolp)<v && slika1(i-stolp)<1 && slika1(i+stolp)>=1)
		%%|| ((i-stolp-1)>0 && slika1(i-stolp-1)>=1 && slika1(i+stolp+1)<1)
		%%|| ((i-stolp+1)>0 && slika1(i-stolp+1)>=1 && slika1(i+stolp-1)<1))
		)
		slika2(i) = 0;
	else
		slika2(i) = 1;
	end;
	else slika2(i) = 1;
	end;


end;

%slika3 = zeros(size(slika2));
%	for i=1:v
%		if(slika2(i)==1)
%			slika3(i)=0;
%		else
%			slika3(i)=1;
%		end;
		
	
%	end;




end;