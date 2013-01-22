function [magnitude, direction] = sobel(imgin)

dx = double(imfilter(imgin, fspecial('sobel')));
dy = double(imfilter(imgin, fspecial('sobel')'));

magnitude = sqrt(dx .^ 2 + dy .^ 2);
direction = atan2(dx, dy);


