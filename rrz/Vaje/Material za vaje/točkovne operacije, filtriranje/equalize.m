function [ imgout ] = equalize(imgin)

h = histogram(imgin, 256);

H = cumsum(h) * 255;

imgout = uint8(H(imgin+1));

