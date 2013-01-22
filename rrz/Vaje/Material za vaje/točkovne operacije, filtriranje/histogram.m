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
h = h / sum(h) ;

% izracunaj vhodno vrednost za celice histograma
celice = ((1 : nbins) - 1) .* f;

