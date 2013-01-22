function [C] = circles(imgin, N)

%rows, columns
%0 = crna, 255 = bela

slika = autothreshold(imgin); 


M = [0 1 0; 0 1 0; 0 1 0];		%matrika za sirjenje in krcenje

%sirjenje
for i=1:5
	slika = imdilate(slika,M);
end;

%krcenje
for i=1:5
	slika = imerode(slika,M);
end;

%[90,100] najbols do zdej
%ta se bols samo more bit M =[0 1 0;0 1 0; 0 1 0] ----[95,100]
radij = [95,100];
%C1	 = imdilate(slika,4); %siritev
%C = imerode(C1,4);			%krcenje
max = [];
robovi = edge(slika);%,"canny");			%dobimo robove
krogi = hough_circle(robovi,radij);
ekstrem = sort(krogi(:),"descend")(N);	%Posortiramo dobljene kroge najdemo ekstrem...vzamemo N-ti po vrsti
[y,x,z] = ind2sub(size(krogi),find(krogi>=ekstrem));	%dobimo x, y, z tistih krogov, ki se imajo za ekstreme...poiscemo vse tiste, ki so vecji ali enaki od N-tega po vrsti
C(:,1) = x;			%shranimo v C
C(:,2) = y;
C(:,3) = radij(z);

end;


%%%%%%%%%%%%%%%%autothreshold%%%%%%%%%%%%%%%%%%%
function [ imgout ] = autothreshold(imgin)
h = histogram(imgin, 256);
mine = 10000;
t = 0;
for i = 2:length(h)-1
h1 = h(1:i+1);
h2 = h(i:end);
h1 = h1(h1 > 0);
h2 = h2(h2 > 0);
e1 = -sum(h1 .* log(h1));
e2 = -sum(h2 .* log(h2));
if (mine > abs(e1 - e2))
mine = abs(e1 - e2);
t = i;
end;
end;
imgout = threshold(imgin, t);
end;

%%%%%%%%%%%%%%histogram%%%%%%%%%%%%%%%%%%%
function [ h, celice ] = histogram(imgin , nbins)

pixels = double(imgin(:));
h = zeros(1,nbins);    % inicializiramo histogram

f = 256 / (nbins);

% pretvorba vrednosti elementov v indekse histograma
idx = floor(pixels ./ f) + 1; 

% moznost 1: povecajmo vsebino pripadajoce celice v histogramu
%for i = idx'
%  h(i) = h(i) + 1;
%end

% moznost 2: nastavimo vsebino pripadajoce celice v histogramu
for i = 1:nbins
  h(i) = sum(idx == i);
end

% normaliziraj histogram, da je vsota celic (integral) enaka 1
%h = h / sum(h) ;

% izracunaj vhodno vrednost za celice histograma
celice = ((1 : nbins) - 1) .* f;
end;