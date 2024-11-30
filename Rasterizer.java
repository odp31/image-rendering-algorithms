public class Rasterizer {
  private int[][] pixelBuffer;
  private int width, height;

  public Rasterizer(int width, int height) {
    this.width = width;
    this.height = height;
    pixelBuffer = new int[height][width];
  }
  public void drawlLine(int x1, int y1, int x2, int y2, int color)
  {
    int dx = Math.abs(x2 - x1);
    int dy = Math.abs(y2 - y1);
    int sx = x1 < x2 ? 1 : -1;
    int sy = y1 < y2 ? 1 : -1;
    int err = dx - dy;
    while(true)
      {
        setPixel(x1, y1, color);
        if(x1 == x2 && y1 == y2) {
          break;
        }
        int e2 = 2 * err;
        if(e2 > -dy) {
          err -= dy;
          x1 += sx;
        }
        if(e2 < dx) {
          err += dx;
          y1 += sy;
        }
      }
    }
    private void setPixel(int x, int y, int color) {
      if(x >= 0 && x < width && y >= 0 && y < height) {
        pixelBuffer[y][x] = color;
      }
    }
}
  
