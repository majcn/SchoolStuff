function [K] = gauss2d(sigma1, sigma2)

K = gauss(sigma1)' * gauss(sigma2);

K = K ./ sum(sum(K));

