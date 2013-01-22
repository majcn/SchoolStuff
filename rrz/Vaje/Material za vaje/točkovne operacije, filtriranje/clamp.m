function [ imgout ] = clamp(imgin, vmin, vmax)

imgout = imgin;
imgout(imgout > vmax) = vmax;
imgout(imgout < vmin) = vmin;

