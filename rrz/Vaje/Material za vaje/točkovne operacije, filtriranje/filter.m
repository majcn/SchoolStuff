function [ imgout ] = filter(imgin, kernel)

ksize = size(kernel);
isize = size(imgin);

ksize2 = floor(ksize / 2);

imgout = zeros(isize);

if (ksize(1) > 1)
idxu = (-ksize2(1):ksize2(1)) + 1;
else 
idxu = 1;
end;

if (ksize(2) > 1)
idxv = (-ksize2(2):ksize2(2)) + 1;
else 
idxv = 1;
end;

imgin = double(imgin);

for u = ksize2(1) + 1:(isize(1) - ksize2(1) - 1)

	for v = ksize2(2) + 1:(isize(2) - ksize2(2) - 1)

		imgout(u, v) = sum(sum(imgin(idxu + u, idxv + v) .* kernel));

	end;

end;

imgout = uint8(imgout);
