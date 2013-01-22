function [M] = matching(imgin, tplin, similarity)

[h w] = size(imgin);
[th tw] = size(tplin);
sy = th / 2;
sx = tw / 2;

M = zeros(h-th, w-tw);

imgin = double(imgin);
tplin = double(tplin);

switch(similarity)
case 'absdiff'
	for j = 1:h-th
		for i = 1:w-tw
			region = imgin(j:j+th-1, i:i+tw-1);
			M(j,i) = sum(sum(abs(tplin - region)));
		end
	end
case 'maxdiff'
	for j = 1:h-th
		for i = 1:w-tw
			region = imgin(j:j+th-1, i:i+tw-1);
			M(j,i) = max(max(abs(tplin - region)));
		end
	end
case 'ssd'
	for j = 1:h-th
		for i = 1:w-tw
			region = imgin(j:j+th-1, i:i+tw-1);
			M(j,i) = sum(sum((tplin - region) .^ 2));
		end
	end
	M = sqrt(M);
case 'ncc'
	tplr = sum(sum(tplin .^ 2));
	for j = 1:h-th
		for i = 1:w-tw
			region = imgin(j:j+th-1, i:i+tw-1);
			M(j,i) = sum(sum(tplin .* region)) / sqrt(sum(sum(region .^ 2)) * tplr);
		end
	end
case 'chamfer'
	tplin = tplin ./ 255;
	D = bwdist(double(imgin > 0));
	for j = 1:h-th
		for i = 1:w-tw
			region = D(j:j+th-1, i:i+tw-1);
			M(j,i) = sum(sum(tplin .* region)); 
		end
	end
	M = M ./ sum(sum(tplin));
end
