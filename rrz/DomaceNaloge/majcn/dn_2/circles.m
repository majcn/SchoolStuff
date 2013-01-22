function [C] = circles(imgin, N, R=[70:90])
  mask = [1 1 1;1 1 1;1 1 1]; %8-sosednost
  imgin = autothreshold(imgin);
  imgin = imerode(imgin, mask);
  imgin = imerode(imgin, mask);
  imgin = imerode(imgin, mask);
  %3x pomanjsamo in 3x povecamo (pac moja izbrna stevilka, nima posebnega pomena)
  imgin = imdilate(imgin, mask);
  imgin = imdilate(imgin, mask);
  imgin = imdilate(imgin, mask);
  
  %bela je FOREGROUND
  
  %C = zeros(N,3);
  edges = edge(imgin, "canny");
  H = hough_circle(edges, R);
  extrem = sort(H(:), "descend")(N);
  [y,x,z] = ind2sub(size(H), find(H>=extrem));
  C = zeros(length(z),3);
  C(:,1) = x;
  C(:,2) = y;
  C(:,3) = R(z);
endfunction

function [imgout] = autothreshold(imgin, beginColor=10, endColor=200)
  data = histogram(imgin, beginColor, endColor);
  t = Shanhbag(data, beginColor, endColor);

  imgout = imgin;

  imgout(imgout >= t) = 255;
  imgout(imgout < t) = 0;
endfunction

function [threshold] = Shanhbag(data, beginColor, endColor)
  K = endColor-beginColor+1;
  P1 = zeros(1,K);
  P2 = zeros(1,K);

  norm_histo = data / sum(data);
  
  P1 = cumsum(norm_histo);
  P2 = 1 - P1;
  
  % Determine the first non-zero bin
  first_bin = find(P1)(1);
  % Determine the last non-zero bin
  last_bin = find(P2)(end);

  % Calculate the total entropy each gray-level
  % and find the threshold that maximizes it 
  for it=first_bin:last_bin
    % Entropy of the background pixelsđ
    term = 0.5 / P1(it);
    range = 1:it;
    ent_back = sum(-norm_histo(range) .* log(1 - term * P1(range))) * term;

    % Entropy of the object pixels
    term = 0.5 / P2(it);
    range = (it+1):K;
    ent_obj = sum(-norm_histo(range) .* log(1 - term * P2(range))) * term;

    % Total entropy
    tot_ent = abs(ent_back - ent_obj);

    if (it == first_bin || tot_ent < min_ent)  %pac ko gre prvic cez ga nastavi
      min_ent = tot_ent;
      threshold = it;
    endif
  end
endfunction

function [h] = histogram(imgin, beginColor, endColor)
  pixels = double(imgin(:));
  h = zeros(1,endColor-beginColor);

  for i = beginColor:endColor
    h(i-beginColor+1) = sum(pixels == i);
  end
endfunction