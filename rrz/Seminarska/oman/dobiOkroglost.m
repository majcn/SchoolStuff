%vhod = crno bela slika - 0 je odzadje, 1 ali vec je regija
%izhod = okroglost(stevilo)
function [okr] = dobiOkroglost(slika)

chain = dobiChain(slika);

povrsina = dobiPovrsino(slika);
obseg = dobiObseg(chain);
obseg2 = obseg^2;
okr = 4*pi*(povrsina/obseg2);


end;