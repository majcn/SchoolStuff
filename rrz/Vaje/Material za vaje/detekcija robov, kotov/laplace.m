function [imgout] = laplace(imgin, alpha)

f = [0 1 0; 1 -4 1; 0 1 0]

imgout = imgin - imfilter(imgin, f) .* alpha;





