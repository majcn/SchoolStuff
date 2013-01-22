% inicializacija spremenljivk za 3. vaje

fri = rgb2gray(imread('fri.jpg'));
frisize = size(fri);

% 1. del - operacije na slikovnih elementih

fri_pale = uint8( (fri - 128) * 0.5 + 128);


% 2. del - filtri

fri_noise = uint8(fri + rand(size(fri)) * 30);

fri_saltpepper = fri;
fri_saltpepper(rand(frisize) > 0.99) = 0;
fri_saltpepper(rand(frisize) < 0.01) = 255;
