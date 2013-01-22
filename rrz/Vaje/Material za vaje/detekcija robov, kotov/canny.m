function [edges] = canny(imgin, sigma, hmax, hmin)

  blur = double(imfilter(imgin, fspecial('gaussian', floor([sigma * 3 + 1, sigma * 3 + 1]), sigma)));

  [h, w] = size(imgin);
  [magnitude, orientation] = sobel(blur);

  angle = floor(((orientation+pi)/pi)*4) + 1;
  angle(angle == 9) = 8;
  angle(angle > 4) = 9 - angle(angle > 4);

% Nonmaximum surpression

  for u = 2:(h - 1)

	for v = 2:(w - 1)

		r = magnitude((-1:1) + u, (-1:1) + v);
		r = r(:);
		if any(r(5) < r([1 2 3 4 6 7 8 9]))
			magnitude(u, v) = 0;
		end;
	end;
  end;
 
%  Odkomentiraj za vizualizacijo
%  viz = zeros(h, w, 3);
%  viz(:, :, 1) = (angle == 1 | angle == 4) & magnitude > hmin;
%  viz(:, :, 2) = (angle == 2 | angle == 4) & magnitude > hmin;
%  viz(:, :, 3) = angle == 3 & magnitude > hmin;
%  imshow(viz);

% Manjka še zadnja faza: sledenje robovom z uporabo histerez:
% - Začni slediti rob če vrednost magnitude > hmax
% - Sledi dokler ni vrednost magnitude < hmin
%

