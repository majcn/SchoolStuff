%http://rsbweb.nih.gov/ij/plugins/download/AutoThresholder.java

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