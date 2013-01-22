function [m] = cmoment(imgin, p, q)
  m00 = sum(sum(imgin));
  [v,u] = ind2sub(size(imgin), find(imgin));
  m10 = sum(u);
  m01 = sum(v);
  x_ = m10/m00;
  y_ = m01/m00;
  
  m = sum(((u-x_).^p).*((v-y_).^q));
endfunction