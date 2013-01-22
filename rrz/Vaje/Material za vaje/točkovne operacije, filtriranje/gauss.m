function [K] = gauss(sigma)

x = [-round(3.0*sigma):1:round(3.0*sigma)] ;

g = exp( - x .* x / (2 * sigma * sigma));

K = g / sum(g); 


