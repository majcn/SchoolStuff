function [cim, r, c] = harris(imgin, sigma, thresh, radius)
    
  imgin = double(imgin);
  [h, w] = size(imgin);
  dx = [-1 0 1; -1 0 1; -1 0 1];
  dy = dx';
    
  Ix = imfilter(imgin, dx, 'same');
  Iy = imfilter(imgin, dy, 'same');    

  g = fspecial('gaussian', max(1, round(3*sigma)), sigma);
    
  Ix2 = imfilter(Ix .^ 2, g, 'same');
  Iy2 = imfilter(Iy .^ 2, g, 'same');
  Ixy = imfilter(Ix .* Iy, g, 'same');

  k = 0.04;
  cim = (Ix2 .* Iy2 - Ixy .^ 2) - k * (Ix2 + Iy2) .^ 2; 

% Nonmaximum surpression
  for u = 2:(h - 1)

	for v = 2:(w - 1)

		r = cim((-1:1) + u, (-1:1) + v);
		r = r(:);
		if any(r(5) < r([1 2 3 4 6 7 8 9]))
			cim(u, v) = 0;
		end;
	end;
  end;		    

% Manjka še zadnji korak: odstranjevanje kotov, ki so preblizu en drugemu
% - sestavi urejen seznam zaznanih kotov glede na njihovo izrazitost
% - dodaj kot v končni seznam, če ni v razdalji radius nobenega drugega kota

