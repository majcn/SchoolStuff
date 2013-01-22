function [ imgout ] = threshold(imgin, t)

imgout = imgin;
imgout(imgout >= t) = 255;
imgout(imgout < t) = 0;

