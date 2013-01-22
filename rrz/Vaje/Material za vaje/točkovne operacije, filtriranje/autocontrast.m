function [ imgout ] = autocontrast(imgin)

vmin = double(min(min(imgin)));
vmax = double(max(max(imgin)));

imgout = uint8((double(imgin) - vmin) ./ ( vmax - vmin ) .* 255);
