%vhod = crno bela slika - 0 je odzadje, 1 ali vec je regija
%izhod = povrsina(stevilo belih pikslov) 
function [p] = dobiPovrsino(slika)

%presteje slikovne elemente na sliki
%zapoljnen element je 1 ali vec
velikost = size(slika);
v = velikost(1)*velikost(2);
p=0;
for i=1:v
	if(slika(i)>=1)
			p++;
	end;
end;



end;