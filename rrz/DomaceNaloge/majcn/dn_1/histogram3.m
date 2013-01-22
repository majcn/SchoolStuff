function [h] = histogram3(imgin , bins)

K = 256; 
f = K ./ bins;
h = zeros(bins); % h = zeros(bins(1),bins(2),bins(3));

%idx = pixli razporejeni v bine: [R1,G1,B1;R2,G2,B2;...] %R1 = rdeca barva od 1 do bins(rdeca)
idx = [];
for i=1:3  
  idx = [idx;(floor(double(imgin(:,:,i)(:)) ./ f(i)) + 1)'];
end
idx = idx';

for i=1:rows(idx)
  h(idx(i,1), idx(i,2), idx(i,3)) += 1;
end

%normaliziraj (am.. zakaj je to potrebno?)
h = h / sum(sum(sum(h)));