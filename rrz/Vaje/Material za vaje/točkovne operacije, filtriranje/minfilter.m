function [ imgout ] = minfilter(imgin, region)

ksize = [region region];
isize = size(imgin);

ksize2 = floor(ksize / 2);

imgout = zeros(isize - ksize + 1);

idxu = (-ksize2(1):ksize2(1)) + 1;
idxv = (-ksize2(2):ksize2(2)) + 1;

for u = ksize2(1):(isize(1) - ksize2(1) - 1)

	for v = ksize2(2):(isize(2) - ksize2(2) - 1)
		imgout(u, v) = min(min(imgin(idxu + u, idxv + v)));
	end;
end;

imgout = uint8(imgout);
