%Vrne obseg, ki ga izracuna iz chain kode
%chain = chain koda slike, za katero zelimo izracunati obseg
function [o] = dobiObseg(chain)

v = length(chain);

skupaj = 0;

for i=1:v
	if(chain(i)==0 || chain(i)==2 || chain(i)==4 || chain(i)==6)
			skupaj++;
	else
		skupaj = skupaj + sqrt(2);
			
	end;
end;

o = skupaj *0.95;

end;