//
//  ViewController.m
//  tic_tac_snow
//
//  Created by Hao Zhou on 2/7/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    _height = self.view.frame.size.height;
    _width = self.view.frame.size.width;
    
    [self Initial];
    
    self.x_Pan = [[UIPanGestureRecognizer alloc] initWithTarget:self action:@selector(PanPiece:)];
    [self.x_Pan setMaximumNumberOfTouches:1];
    [self.x_Pan setDelegate:self];
    [self.x_imageView addGestureRecognizer:self.x_Pan];
    
    self.o_Pan = [[UIPanGestureRecognizer alloc] initWithTarget:self action:@selector(PanPiece:)];
    [self.o_Pan setMaximumNumberOfTouches:1];
    [self.o_Pan setDelegate:self];
    [self.o_imageView addGestureRecognizer:self.o_Pan];
    
    [self judgement];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    
}


#pragma initial

-(void)Initial{
    _endPoints = [[NSMutableArray alloc] initWithObjects:nil];
    _step_num = 0;
    _gameRun = true;
    [self addInitViews];

}

#pragma addViews

-(void)addInitViews{
    
    
    UIImageView* grid11 = self.cellImage1;
    UIImageView* grid12 = self.cellImage2;
    UIImageView* grid13 = self.cellImage3;
    UIImageView* grid21 = self.cellImage4;
    UIImageView* grid22 = self.cellImage5;
    UIImageView* grid23 = self.cellImage6;
    UIImageView* grid31 = self.cellImage7;
    UIImageView* grid32 = self.cellImage8;
    UIImageView* grid33 = self.cellImage9;
    
    _gridArray = [[NSMutableArray alloc] initWithObjects:[[NSMutableArray alloc] initWithObjects: grid11, grid12, grid13, nil], [[NSMutableArray alloc] initWithObjects: grid21, grid22, grid23, nil], [[NSMutableArray alloc] initWithObjects: grid31, grid32, grid33, nil], nil];
    
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            UIImageView* gridCell = [[self.gridArray objectAtIndex:i] objectAtIndex:j];
            gridCell.image = nil;
            gridCell.backgroundColor = nil;
            gridCell.tag = i*10 +j;
            [self.view addSubview:[[self.gridArray objectAtIndex:i] objectAtIndex:j]];
        }
    }
}

#pragma panPiece

-(void)PanPiece: (UIPanGestureRecognizer*)sender{
    
    UIView *piece = [sender view];
    [[piece superview] bringSubviewToFront:piece];
    CGFloat center_y;
    CGFloat center_x;
    NSString *imageName;
    UIImageView *altImage;
    CGPoint basePoint;
    
    if (piece.tag == 0) {

        
        center_y = self.view.frame.size.height - _x_imageView.frame.size.height;
        center_x = _x_imageView.frame.size.height;
        imageName = @"X.png";
        basePoint = _x_imageView.center;
        

    }
    else{

        
        center_y = self.view.frame.size.height - _o_imageView.frame.size.height;
        center_x = self.view.frame.size.width-_o_imageView.frame.size.height;
        imageName = @"O.png";
        basePoint = _o_imageView.center;
        

    }
    
    if ([sender state] == UIGestureRecognizerStateBegan)
        [self playSound:@"torch"];

    if ([sender state] == UIGestureRecognizerStateBegan ||
        [sender state] == UIGestureRecognizerStateChanged) {
        CGPoint translation = [sender translationInView:[piece superview]];
        [piece setCenter:CGPointMake([piece center].x + translation.x,
                                     [piece center].y + translation.y)];
        [sender setTranslation:CGPointZero inView:[piece superview]];
    }
    
    if ([imageName  isEqual: @"X.png"]){
        altImage = _o_imageView;
    }else
        altImage = _x_imageView;
    
    if ([sender state] == UIGestureRecognizerStateEnded) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                UIImageView *gridCell = [[self.gridArray objectAtIndex:i] objectAtIndex:j];
                if (CGRectIntersectsRect(piece.frame, gridCell.frame)) {
                    if(gridCell.image==nil){
                        gridCell.image = [UIImage imageNamed:imageName];
                        [piece setCenter:CGPointMake(center_x,center_y)];
                        [self growandshrink:altImage];

                        [self playSound:@"inCell"];
                        
                        self.step_num += 1;
                        [self judgement];
                        if ([self isWin]) {
                            NSLog(@"someone wins");
                        }
                        break;
                    }else{
                        [self playSound:@"ball"];
                        [sender setTranslation: basePoint inView:[piece superview]];     //CGPointZero
                        [UIView animateWithDuration:4.0 animations:^{
                            [piece setCenter:CGPointMake(center_x,center_y)];
                        }];
                    }
                }
            }
        }

    }
}

#pragma growingandshrinking
-(void)growandshrink: (UIImageView *) image{
    
    [UIImageView animateWithDuration:0.5f
                          animations:^{
                              CGRect frame = image.frame;
                              frame.size.height = 1.0f;
                              frame.size.width = 1.0f;
                              image.frame = frame;
                          }
                          completion:^(BOOL finished){
                          }];
}

#pragma check status
-(void)judgement{

    if (_gameRun && self.step_num%2 == 0) {
        [self.x_imageView setUserInteractionEnabled:YES];
        self.x_imageView.alpha = 1;
        [self.o_imageView setUserInteractionEnabled:NO];
        self.o_imageView.alpha = 0.3f;
    }
    else if (_gameRun && self.step_num%2 != 0) {
        [self.o_imageView setUserInteractionEnabled:YES];
        self.o_imageView.alpha = 1;
        [self.x_imageView setUserInteractionEnabled:NO];
        self.x_imageView.alpha = 0.3f;
    }
    else {
        [self.x_imageView setUserInteractionEnabled:NO];
        self.x_imageView.alpha = 0.5f;
        [self.o_imageView setUserInteractionEnabled:NO];
        self.o_imageView.alpha = 0.5f;
    }
}


#pragma WinOrNot
-(BOOL)isWin{
    UIImageView* grid11 = [[self.gridArray objectAtIndex:0] objectAtIndex:0];
    UIImageView* grid12 = [[self.gridArray objectAtIndex:0] objectAtIndex:1];
    UIImageView* grid13 = [[self.gridArray objectAtIndex:0] objectAtIndex:2];
    UIImageView* grid21 = [[self.gridArray objectAtIndex:1] objectAtIndex:0];
    UIImageView* grid22 = [[self.gridArray objectAtIndex:1] objectAtIndex:1];
    UIImageView* grid23 = [[self.gridArray objectAtIndex:1] objectAtIndex:2];
    UIImageView* grid31 = [[self.gridArray objectAtIndex:2] objectAtIndex:0];
    UIImageView* grid32 = [[self.gridArray objectAtIndex:2] objectAtIndex:1];
    UIImageView* grid33 = [[self.gridArray objectAtIndex:2] objectAtIndex:2];
    
    BOOL iswin = false;
    UIImage *image = nil;
    _endPoints = [[NSMutableArray alloc] initWithObjects:nil];
    
    if ([grid11.image isEqual:grid12.image] && [grid12.image isEqual:grid13.image]) {
        iswin = true;
        image = grid11.image;
        [_endPoints addObject:grid11];
        [_endPoints addObject:grid13];
    }
    else if ([grid21.image isEqual:grid22.image] && [grid22.image isEqual:grid23.image]) {
        iswin = true;
        image = grid21.image;
        [_endPoints addObject:grid21];
        [_endPoints addObject:grid23];
    }
    else if ([grid31.image isEqual:grid32.image] && [grid32.image isEqual:grid33.image]) {
        iswin = true;
        image = grid31.image;
        [_endPoints addObject:grid31];
        [_endPoints addObject:grid33];
    }
    else if ([grid11.image isEqual:grid21.image] && [grid21.image isEqual:grid31.image]) {
        iswin = true;
        image = grid11.image;
        [_endPoints addObject:grid11];
        [_endPoints addObject:grid31];
    }
    else if ([grid12.image isEqual:grid22.image] && [grid22.image isEqual:grid32.image]) {
        iswin = true;
        image = grid12.image;
        [_endPoints addObject:grid12];
        [_endPoints addObject:grid32];
    }
    else if ([grid13.image isEqual:grid23.image] && [grid23.image isEqual:grid33.image]) {
        iswin = true;
        image = grid13.image;
        [_endPoints addObject:grid13];
        [_endPoints addObject:grid33];
    }
    else if ([grid11.image isEqual:grid22.image] && [grid22.image isEqual:grid33.image]) {
        iswin = true;
        image = grid11.image;
        [_endPoints addObject:grid11];
        [_endPoints addObject:grid33];
    }
    else if ([grid13.image isEqual:grid22.image] && [grid22.image isEqual:grid31.image]) {
        iswin = true;
        image = grid13.image;
        [_endPoints addObject:grid13];
        [_endPoints addObject:grid31];
    }
    else
        iswin = false;
    
    if (iswin) {
        
        UIImageView *one = [_endPoints objectAtIndex:0];
        UIImageView *two = [_endPoints objectAtIndex:1];
        
        CGPoint start =CGPointMake([one center].x, [one center].y);//start point
        CGPoint end =CGPointMake([two center].x, [two center].y);//end point

        CGFloat xOne = start.x;
        CGFloat yOne = start.y;
        CGFloat xTwo = end.x;
        CGFloat yTwo = end.y;
        
        NSLog(@"the point array is %@",_endPoints);

        NSLog(@"Points are %f%f%f%f",xOne,yOne,xTwo,yTwo);
        
        [self drawLine:start :end];
        

        
        NSString *str = nil;
        if ([image isEqual:_x_imageView.image]) {
            str = @"x wins, congratulations!";
        }
        else str = @"o wins, congratulations!";
        UIAlertView *endAlert = [[UIAlertView alloc] initWithTitle:@"Game Over!" message:str delegate:self
                                                 cancelButtonTitle:@"Clean Up" otherButtonTitles: nil];

        
        [self playSound:@"yahoo"];
        [endAlert show];
    }
    
    if (iswin == false){
        BOOL occupiedAll = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                UIImageView* gridCell = [[self.gridArray objectAtIndex:i] objectAtIndex:j];
                if (gridCell.image == nil){
                    occupiedAll = false;
                };

            }
        }
        
        if (occupiedAll) {
            _step_num = 0;
            NSString *str = @"Nobody Wins, Try Again!";
            UIAlertView *endAlert = [[UIAlertView alloc] initWithTitle:@"Game Over!" message:str delegate:self
                                                 cancelButtonTitle:@"Clean Up" otherButtonTitles: nil];
        
            [self playSound:@"sad"];
            [endAlert show];
        }
    }
    
    
    return iswin;
    
}

-(void)drawLine: (CGPoint) first : (CGPoint) second{
    _winLine = [[DrawLine alloc] initWithFrame:CGRectMake(0, 0, _width, _height)];
    _winLine.firstPoint = first;
    _winLine.secondPoint = second;
    _winLine.backgroundColor = [UIColor clearColor];
    [self.view addSubview:_winLine];
}



#pragma alertForGameInfo
- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
    NSString *title = [alertView buttonTitleAtIndex:buttonIndex];
    
    
    if([title isEqualToString:@"Clean Up"])
    {
        [self.winLine setCenter:CGPointMake(_width*2, _height*2)];

        [self moveItems];
        [self playSound:@"comet"];
        [self gameRestart];

    }
    
    if([title isEqualToString:@"New Game"])
    {
        [self.winLine setCenter:CGPointMake(_width*2, _height*2)];

        [self viewDidLoad];

    }
}

- (void)gameRestart{

    UIAlertView *endAlert = [[UIAlertView alloc] initWithTitle:@"Game Over!" message:@"Start A New Game" delegate:self
                                             cancelButtonTitle:@"New Game" otherButtonTitles: nil];
    [endAlert show];
    
    
}

#pragma move_xando
- (void)moveItems{
    
    [UIView animateWithDuration:2.0 animations:^{
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                UIImageView* gridCell = [[self.gridArray objectAtIndex:i] objectAtIndex:j];
                [gridCell setCenter:CGPointMake(gridCell.center.x, gridCell.center.y*5)];

            }
        }
    }];
    
}

#pragma playsound

- (void)playSound:(NSString*)path{
    
    self.soundPath = [[NSBundle mainBundle]pathForResource:path ofType:@"wav"];
    self.audio = [[AVAudioPlayer alloc] initWithContentsOfURL:[NSURL fileURLWithPath:self.soundPath] error:NULL];
    self.audio.meteringEnabled = YES;
    self.audio.delegate = self;
    [self.audio prepareToPlay];
    [self.audio play];
}


- (IBAction)helpButton:(id)sender {
    NSString *message = @"This is a tic-tac-toe game. The users can pick a label from X and O, and drag to place it to the correct cell in grid provided. When three Xs or Os line up vertically, horizontally, or diagonally, the user wins.";

    
    UIAlertView *help = [[UIAlertView alloc] initWithTitle:@"HELP" message: message delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil];
    
    
    [help show];
    
}



@end










