function [imgout] = backproject3(imgin, h)

%init
K = 256;
bins = size(h) - 1;
f = K ./ bins;
imgout = zeros(size(imgin)(1), size(imgin)(2));
ind = [];


for i=1:3  
  ind(:,:,i) = ceil((double(imgin(:,:,i)) + 1) ./ f(i)) + 1;
end

for i=1:rows(imgout)
  for j=1:columns(imgout)
    imgout(i,j) = h(ind(i,j,1), ind(i,j,2), ind(i,j,3));
  end
end