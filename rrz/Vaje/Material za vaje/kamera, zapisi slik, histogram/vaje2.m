% inicializacija spremenljivk za 2. vaje

slika1 = uint8(zeros(100, 100));
slika1(1:50, 1:50) = 255;
slika1(51:100, 51:100) = 255;

slika2 = uint8(zeros(100, 100));
slika2(1:50, :) = 255;

slika3 = uint8(rand(500, 500) * 255);

slika4 = uint8(min(max(0, normrnd(128, 20, [500, 500])), 255));

fri_color = imread('fri.jpg');
fri_gray = rgb2gray(fri_color);

